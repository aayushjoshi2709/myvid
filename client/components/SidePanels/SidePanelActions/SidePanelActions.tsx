import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import SubscriptionsOutlinedIcon from "@mui/icons-material/SubscriptionsOutlined";
import AccountCircleOutlinedIcon from "@mui/icons-material/AccountCircleOutlined";
import UploadOutlinedIcon from "@mui/icons-material/UploadOutlined";
import SidePanelActionItem from "./SidePanelActionItem/SidePanelActionItem";

interface SidePanelActionsProps {
  areLabelVisible: boolean;
}

const SidePanelActions = ({ areLabelVisible }: SidePanelActionsProps) => {
  return (
    <ul>
      <SidePanelActionItem
        label="Home"
        url="/"
        areLabelVisible={areLabelVisible}
        imageIcon={<HomeOutlinedIcon />}
      />

      <SidePanelActionItem
        label="Subscriptions"
        url="/feed/subscriptions"
        areLabelVisible={areLabelVisible}
        imageIcon={<SubscriptionsOutlinedIcon />}
      />

      <SidePanelActionItem
        imageIcon={<UploadOutlinedIcon />}
        url="/upload"
        label="Upload video"
        areLabelVisible={areLabelVisible}
      />

      <SidePanelActionItem
        imageIcon={<AccountCircleOutlinedIcon />}
        url="/me"
        label="You"
        areLabelVisible={areLabelVisible}
      />
    </ul>
  );
};

export default SidePanelActions;
