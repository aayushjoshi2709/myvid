import SidePanelActions from "../SidePanelActions/SidePanelActions";

interface SidePanelProps {
  areLabelVisible: boolean;
}

const SidePanel = ({ areLabelVisible }: SidePanelProps): React.ReactElement => {
  return (
    <aside className="sm:w-1/12 bg-white h-full md:w-2/8 lg:w-2/10 xl:w-1/6 border-r  border-gray-300">
      <SidePanelActions areLabelVisible={areLabelVisible} />
    </aside>
  );
};

export default SidePanel;
