import Image from "next/image";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";
import UploadVideoButton from "@/components/UploadVideoButton/UploadVideoButton";
import Search from "@/components/Search/Search";
import MyvidLogo from "../MyvidLogo/MyvidLogo";
import Link from "next/link";

interface TopNavProps {
  menuButtonClickAction: () => void;
}

const TopNav = ({ menuButtonClickAction }: TopNavProps): React.ReactElement => {
  return (
    <div
      id="top-nav"
      className="sticky top-0  w-full bg-white flex justify-between items-center flex-row p-3 px-6 border-b border-gray-300 "
    >
      <div id="menu">
        <button onClick={menuButtonClickAction}>
          <MenuOutlinedIcon />
        </button>
      </div>
      <Link href="/" className="hidden md:flex md:flex-row">
        <MyvidLogo height={40} width={160} />
      </Link>
      <div className="justify-center items-center flex-1 mx-10 hidden md:block">
        <Search />
      </div>
      <div className="hidden gap-4 md:flex md:flex-row">
        <div>
          <UploadVideoButton />
        </div>
        <div id="profile">
          <Image
            id="video-logo"
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
