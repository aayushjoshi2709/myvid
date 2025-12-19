import ChannelInfo from "./ChannelInfo";
interface VedioDetailsInterface {
  id: string;
  thumbnail: string;
  channel: ChannelInfo;
  title: string;
  createdAt: string;
  viewCount: number;
  description: string;
}

export default VedioDetailsInterface;
