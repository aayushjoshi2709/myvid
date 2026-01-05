import UserInfo from "./UserInfo";
interface VideoDetailsInterface {
  id: string;
  thumbnail: string;
  createdBy: UserInfo;
  title: string;
  createdAt: string;
  viewCount: number;
  description: string;
}

export default VideoDetailsInterface;
