import { CreateInfoForVedio } from "./UserInfo";
interface VideoDetailsInterface {
  id: string;
  thumbnail: string;
  createdBy: CreateInfoForVedio;
  title: string;
  createdAt: string;
  viewCount: number;
  description: string;
}

export default VideoDetailsInterface;
