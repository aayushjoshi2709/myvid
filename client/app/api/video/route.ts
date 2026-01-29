import { ApiClient } from "@/app/lib/api";
import { VideoUpload, VideoUploadResponse } from "@/common/interfaces/video";
import { Routes } from "@/common/routes/routes";
import { NextRequest, NextResponse } from "next/server";

export async function POST(req: NextRequest){
    const body: VideoUpload = await req.json();
    const apiClient = new ApiClient<VideoUploadResponse>(process.env.HOST_URL as string);
    const data = apiClient.post(Routes.server.vedio.ADD,body)
    const response = NextResponse.json({ success: true, data: data });
    return response;
}