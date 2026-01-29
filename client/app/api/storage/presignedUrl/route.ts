import { ApiClient } from "@/app/lib/api";
import { PresignedUrlBody, PresignedUrlResponse } from "@/common/interfaces/PresignedUrl";
import { Routes } from "@/common/routes/routes";
import { NextResponse } from "next/server";

export async function POST(req: Request){
    const apiClient = new ApiClient<PresignedUrlResponse>(process.env.HOST_URL as string)
    const body: PresignedUrlBody = await req.json();
    const data: PresignedUrlResponse = await apiClient.post(Routes.server.storage.PRESIGNED_URL, body)
    return NextResponse.json(data)
}