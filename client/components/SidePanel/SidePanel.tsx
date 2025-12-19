import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import SubscriptionsOutlinedIcon from "@mui/icons-material/SubscriptionsOutlined";
import AccountCircleOutlinedIcon from "@mui/icons-material/AccountCircleOutlined";
import Link from "next/link";
const SidePanel = (): React.ReactElement => {
  return (
    <div className="sm:w-1/12 md:w-2/8 lg:w-2/10 xl:w-1/6 mt-0 mr-4 border-r border-gray-300 p-2 px-4 sm:px-2">
      <ul>
        <li>
          <Link href={"/"}>
            <div className="p-2 flex gap-4 flex-wrap">
              <HomeOutlinedIcon />
              <p>Home</p>
            </div>
          </Link>
        </li>
        <li>
          <Link href={"/feed/subscriptions"}>
            <div className="p-2 flex gap-4 flex-wrap">
              <SubscriptionsOutlinedIcon />
              <p>Subscriptions</p>
            </div>
          </Link>
        </li>
        <li>
          <Link href={"/me"}>
            <div className="p-2 flex gap-4 flex-wrap">
              <AccountCircleOutlinedIcon />
              <p>You</p>
            </div>
          </Link>
        </li>
      </ul>
    </div>
  );
};

export default SidePanel;
