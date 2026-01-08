import { NextResponse } from "next/server";
import { HttpStatusCode } from "axios";

interface LoginRequestBody{
    username: string;
    password: string;
}

export async function POST(req:Request){
    const body: LoginRequestBody = await req.json();

    const res:Response = await fetch(`${process.env.HOST_URL}/api/v1/user/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });


    if(!res.ok){
        return NextResponse.json(
            {message: "Invalid credentials"},
            {status: HttpStatusCode.Unauthorized}
        )
    }

    const data = await res.json();
    const response = NextResponse.json({ success: true });

    response.cookies.set("accessToken", data.accessToken, {
        httpOnly: true,
        secure: true,
        sameSite: "strict",
        path: "/",
        maxAge: 60 * 60,
    });

    return response;
}