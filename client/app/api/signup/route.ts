import { NextResponse } from "next/server";
import { HttpStatusCode } from "axios";

interface SignupRequestBody{
    username: string;
    password: string;
    email:string;
    phoneNo:number;
    firstName:string;
    lastName:string;
}

export async function POST(req:Request){
    const body: SignupRequestBody = await req.json();

    console.log(body)


    const res:Response = await fetch(`${process.env.HOST_URL}/api/v1/user`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });


    if(!res.ok){
        return NextResponse.json(
            {message: res.statusText},
            {status: res.status}
        )
    }

    const data = await res.json();
    const response = NextResponse.json({ success: true, data: data });
    return response;
}