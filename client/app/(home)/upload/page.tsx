"use client";
import { s3FileUpload } from "@/common/functions/S3FileUpload";
import { PresignedUrlStorageTypes } from "@/common/interfaces/PresignedUrl";
import { VideoUpload } from "@/common/interfaces/video";
import TextInput from "@/components/TextInput/TextInput";

import UploadIcon from "@mui/icons-material/UploadSharp";
import { useState } from "react";
import toast from "react-hot-toast";
const VideoUploadPage = () => {
  const [loading, setLoading] = useState<boolean>(false);
  const [title, setTitle] = useState<string>("");
  const [description, setDescription] = useState<string>("");
  const [thumbnailUrl, setThumbnailUrl] = useState<string>("");
  const [videoUrl, setVideoUrl] = useState<string>("");

  async function uploadvideo(e: React.FormEvent) {
    e.preventDefault();
    setLoading(true);
    const body: VideoUpload = {
      title,
      description,
      thumbnailUrl,
      videoUrl,
    };
    // const response = await fetch();
    const response: Response = await fetch("/api/video", {
      method: "POST",
      body: JSON.stringify(body),
    });

    setLoading(false);
    if (!response.ok) {
      toast.error("Error video file uploading file");
      return;
    }
    toast.success(
      "video updated successfully and will be avaiable to watch in few minutes"
    );
  }

  async function handlevideoFileUpload(
    e: React.ChangeEvent<HTMLInputElement>
  ): Promise<void> {
    const file = e.target.files?.[0];
    if (file) {
      setVideoUrl(await s3FileUpload(file, PresignedUrlStorageTypes.VIDEO));
    }
  }
  async function handleThumbnailUpload(
    e: React.ChangeEvent<HTMLInputElement>
  ): Promise<void> {
    const file = e.target.files?.[0];
    if (file) {
      setThumbnailUrl(await s3FileUpload(file, PresignedUrlStorageTypes.IMAGE));
    }
  }
  return (
    <div className="justify-center  mt-16 flex-1 w-full h-screen items-center">
      <form className="max-w-sm border-gray-50 rounded-2xl shadow-2xl border mx-auto p-4">
        <h1 className="text-4xl text-center my-4">Video Upload</h1>
        <TextInput
          title="Title"
          inputType="text"
          id="title"
          placeHolder="Enter the title of video"
          required={true}
          value={title}
          onChange={(value) => setTitle(value as string)}
        />

        <TextInput
          title="Description"
          inputType="text"
          id="description"
          placeHolder="Enter the desciption of video"
          required={true}
          onChange={(value) => setDescription(value as string)}
        />

        <div className="mb-5">
          <label htmlFor="videoFile" className="font-medium text-heading">
            video File
          </label>
          <input
            onChange={handlevideoFileUpload}
            type="file"
            id="videoFile"
            className="border rounded-xl border-[#bbbbbb] text-sm rounded-base w-full px-3 py-2.5 shadow-xs"
          />
        </div>

        <div className="mb-5">
          <label htmlFor="thumbnailFile" className="font-medium text-heading">
            Thumbnail File
          </label>
          <input
            onChange={handleThumbnailUpload}
            type="file"
            id="thumbnailFile"
            className="border rounded-xl border-[#bbbbbb] text-sm rounded-base w-full px-3 py-2.5 shadow-xs"
          />
        </div>
        <hr className=" border-gray-100 border" />
        <div className="my-4 w-full flex justify-center">
          <button
            onClick={uploadvideo}
            className="border rounded-3xl text-center font-bold bg-green-500 text-white border-[#eee2e2] shadow-xl p-2 px-6"
          >
            <UploadIcon />
            {loading ? "Uploading video..." : "Upload video"}
          </button>
        </div>
      </form>
    </div>
  );
};

export default VideoUploadPage;
