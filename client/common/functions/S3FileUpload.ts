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
          "x-amz-meta-name": body.name,
          "x-amz-meta-storagetype": body.storageType
        },
        body: file,
      });

      if (!s3Response.ok) {
        const errorBody = await s3Response.text();

        console.error("S3 upload failed:", {
          status: s3Response.status,
          statusText: s3Response.statusText,
          body: errorBody,
        });

        throw new Error(`S3 upload failed: ${s3Response.status}`);
      }
      return presignedUrlData.originalUrl;
    }

    throw new Error("Error video file uploading file");
  }