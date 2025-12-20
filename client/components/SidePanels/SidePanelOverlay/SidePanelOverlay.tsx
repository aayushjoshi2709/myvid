import Link from "next/link";
import SidePanelActions from "../SidePanelActions/SidePanelActions";
import MyVidLogo from "@/components/MyvidLogo/MyvidLogo";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";
interface SidePanelOverlayProps {
  isVisible: boolean;
  setIsVisible: React.Dispatch<React.SetStateAction<boolean>>;
}

const SidePanelOverlay = ({
  isVisible,
  setIsVisible,
}: SidePanelOverlayProps): React.ReactElement | null => {
  function toggleVisibility() {
    console.log("here is click");
    setIsVisible((visible) => !visible);
  }

  return isVisible ? (
    <div
      className="fixed top-0 left-0 w-full h-full bg-black/70 z-50"
      onClick={toggleVisibility}
    >
      <aside className="sm:w-1/12 bg-white h-full md:w-2/8 lg:w-2/10 xl:w-1/6 border-r  border-gray-300">
        <div className="flex  items-center flex-row p-3 px-6 border-b border-gray-300 ">
          <div id="menu">
            <button onClick={toggleVisibility}>
              <MenuOutlinedIcon />
            </button>
          </div>
          <Link href="/">
            <MyVidLogo height={40} width={160} />
          </Link>
        </div>
        <div className="flex justify-between items-center flex-row">
          <SidePanelActions areLabelVisible={true} />
        </div>
      </aside>
    </div>
  ) : null;
};

export default SidePanelOverlay;
