import { CreateInfoForvideo } from "./UserInfo";

export interface VideoUpload{
  thumbnailUrl: string;
  videoUrl: string;
  title: string;
  description: string;
}


export interface VideoUploadResponse{
  "id": string,
  "thumbnailUrl": string,
  "videoUrl": string,
  "title": string,
  "description": string,
  "updatedAt": string,
  "createdAt": string,
  "createdBy": Partial<CreateInfoForvideo>
}