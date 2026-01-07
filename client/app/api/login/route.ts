import { NextResponse } from "next/server";
import axios from "axios";

interface LoginRequestBody{
    username: string;
    password: string;
}

export async function POST(req:Request){
    const body: LoginRequestBody = await req.json();
    const res = await axios.post(
       `${process.env.HOST_URL}/api/v1/login`,
       body,
       {
        headers: {
            "Content-Type": "application/json",
        },
       }
    )
    const data = res.data;
    const response = NextResponse.json({ success: true });

    response.cookies.set("token", data.accessToken, {
        httpOnly: true,
        secure: true,
        sameSite: "strict",
        path: "/",
    });

    return response;
}