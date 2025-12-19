"use client";
import FormInput from "@/components/FormInput/FormInput";
const Upload = () => {
  return (
    <div className="justify-center flex-1 ">
      <form className="max-w-sm mx-auto p-4">
        <h1 className="text-4xl text-center">Video Upload</h1>
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
        <div className="mb-5 flex gap-2">
          <input
            className="border rounded-2xl bg-[#eaeaea] border-[#bbbbbb] p-1 px-6"
            type="button"
            value="Upload"
          />
          <input
            className="border rounded-2xl bg-[#eaeaea] border-[#bbbbbb] p-1 px-6"
            type="button"
            value="Cancel"
            onClick={(e) => {
              e.preventDefault();
            }}
          />
        </div>
      </form>
    </div>
  );
};

export default Upload;
