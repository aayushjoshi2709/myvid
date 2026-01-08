export enum PresignedUrlStorageTypes{
    IMAGE,
    VIDEO
}

export interface PresignedUrlBody{
  storageType: PresignedUrlStorageTypes,
  name: string
}

export interface PresignedUrlResponse{
    presignedUrl: string,
    originalUrl: string
}