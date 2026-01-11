import { PresignedUrlBody, PresignedUrlResponse, PresignedUrlStorageTypes } from "../interfaces/PresignedUrl";

export async function s3FileUpload(
    file: File,
    fileType: PresignedUrlStorageTypes
  ): Promise<string> {
    const body: PresignedUrlBody = {
      storageType: fileType,
      name: file.name,
    };

    const response: Response = await fetch("/api/storage/presignedUrl", {
      method: "POST",
      body: JSON.stringify(body),
    });

    if (response.ok) {
      const presignedUrlData: PresignedUrlResponse = await response.json();
      const s3Response: Response = await fetch(presignedUrlData.presignedUrl, {
        method: "PUT",
        headers: {
          "Content-Type": file.type,
        },
        body: file,
      });

      console.log(presignedUrlData)

      if (s3Response.ok) {
        return presignedUrlData.originalUrl;
      }
    }

    throw new Error("Error video file uploading file");
  }