import { cookies } from "next/headers";
import { NextResponse } from "next/server";

enum PresignedUrlStorageTypes{
    IMAGE,
    VIDEO
}

interface PresignedUrlBody{
  storageType: PresignedUrlStorageTypes,
  name: string
}

interface PresignedUrlResponse{
    presignedUrl: string,
    originalUrl: string
}

export async function POST(req: Request){
    const cookieStore = await cookies()
    const token = cookieStore.get("accessToken")?.value;
    if(token){
        const body: PresignedUrlBody = await req.json();
        const res:Response = await fetch(`${process.env.HOST_URL}/api/v1/user`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body),
        });

        const data: PresignedUrlBody
    }
    return NextResponse.redirect(new URL("/login", req.url));
}