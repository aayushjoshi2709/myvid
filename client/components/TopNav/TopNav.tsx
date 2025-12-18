import Image from "next/image";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";
import UploadVideoButton from "@/components/UploadVideoButton/UploadVideoButton";
import Search from "@/components/Search/Search";
const TopNav = (): React.ReactElement => {
  return (
    <div
      id="top-nav"
      className="flex justify-between items-center flex-row p-3 px-6 border-b border-gray-300 "
    >
      <div id="menu">
        <MenuOutlinedIcon />
      </div>
      <div className="justify-center items-center flex-1 mx-10">
        <Search />
      </div>
      <div className="flex gap-4">
        <UploadVideoButton />
        <div id="profile">
          <Image
            id="vedio-logo"
            src={"/hq720.jpg"}
            width={48}
            height={48}
            alt="Image for"
            className={`rounded-full`}
          />
        </div>
      </div>
    </div>
  );
};

export default TopNav;
