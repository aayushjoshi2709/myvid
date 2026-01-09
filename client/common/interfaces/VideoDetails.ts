import { CreateInfoForvideo } from "./UserInfo";
interface VideoDetailsInterface {
  id: string;
  thumbnailUrl: string;
  createdBy: CreateInfoForvideo;
  title: string;
  createdAt: string;
  viewCount: number;
  description: string;
  videoUrl:string;
}

export default VideoDetailsInterface;
