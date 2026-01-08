import { cookies } from "next/headers";
import { NextResponse } from "next/server";

export async function POST(){
    const cookieStore = await cookies();
    const token = cookieStore.get("accessToken")?.value;
    if(token){
        await fetch(`${process.env.HOST_URL}/api/v1/logout`,{
            method: "POST",
            headers:{
                Authorization: `Bearer ${token}`
            }
        })
    }

    const res = NextResponse.json({success: true})
        res.cookies.set("accessToken", "", {
        maxAge: 0,
        path: "/",
    });

  return res;

}