import { ApiClient } from "@/app/lib/api"
import { Routes } from "@/common/routes/routes";
import { NextResponse } from "next/server"
import util from "node:util";
import VideoDetailsInterface from "@/common/interfaces/VideoDetails";

export async function GET(
    req:Request,
    context: { params: Promise<{ id: string }> }
){
    const {id} = await context.params;
    const apiClient = new ApiClient<VideoDetailsInterface>(process.env.HOST_URL as string)
    let data = await apiClient.get(util.format(Routes.server.video.GET, id))
    data.videoUrl = process.env.AWS_CDN_PREFIX + data.videoUrl;
    data.thumbnailUrl = process.env.AWS_CDN_PREFIX + data.thumbnailUrl;
    const response = NextResponse.json({ success: true, data: data });
    return response;
}
