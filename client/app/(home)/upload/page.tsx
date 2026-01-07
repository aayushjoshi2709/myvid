"use client";
import FormInput from "@/components/FormInput/FormInput";

import UploadIcon from "@mui/icons-material/UploadSharp";
const VedioUploadPage = () => {
  function uploadVedio() {}
  return (
    <div className="justify-center  mt-16 flex-1 w-full h-screen items-center">
      <form className="max-w-sm border-gray-50 rounded-2xl shadow-2xl border mx-auto p-4">
        <h1 className="text-4xl text-center my-4">Video Upload</h1>
        <FormInput
          title="Title"
          inputType="text"
          id="title"
          placeHolder="Enter the title of video"
          required={true}
        />

        <FormInput
          title="Description"
          inputType="text"
          id="description"
          placeHolder="Enter the desciption of video"
          required={true}
        />

        <FormInput title="Video" inputType="file" id="video" required={true} />

        <FormInput
          title="Thumbnail File"
          inputType="file"
          id="thumbnail"
          required={true}
        />
        <hr className=" border-gray-100 border" />
        <div className="my-4 w-full flex justify-center">
          <button className="border rounded-3xl text-center font-bold bg-green-500 text-white border-[#eee2e2] shadow-xl p-2 px-6">
            <UploadIcon />
            Upload
          </button>
        </div>
      </form>
    </div>
  );
};

export default VedioUploadPage;
