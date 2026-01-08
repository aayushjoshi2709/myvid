import { HttpStatusCode } from "axios";
import { cookies } from "next/headers";
import { NextResponse } from "next/server";

export async function GET(){
    const cookieStore = await cookies();
    const token = cookieStore.get("accessToken")?.value;

    if(token){
        const res:Response = await fetch(`${process.env.HOST_URL}/api/v1/user/me`, {
        headers: { 
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}` 
        },
        });

        if(!res.ok){
            return NextResponse.json(
                {message: res.statusText},
                {status: res.status}
            )
        }
        const data = await res.json();
        const response = NextResponse.json({ data });
        return response;
    }
    const response = NextResponse.json({ message: "Invalid token" }, {status: HttpStatusCode.BadRequest});
    return response;
}