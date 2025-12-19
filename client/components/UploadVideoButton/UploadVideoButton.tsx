import UploadOutlinedIcon from "@mui/icons-material/UploadOutlined";
import Link from "next/link";

const UploadVideoButton = (): React.ReactElement => {
  return (
    <Link href={"/upload"}>
      <button className="p-1 px-4 border border-gray-300 rounded-full">
        <UploadOutlinedIcon />
        Upload Video
      </button>
    </Link>
  );
};

export default UploadVideoButton;
