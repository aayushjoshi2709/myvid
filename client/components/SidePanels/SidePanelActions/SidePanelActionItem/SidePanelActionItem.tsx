import Link from "next/link";

interface SidePanelActionItemProps {
  url: string;
  imageIcon: React.ReactNode;
  label: string;
  areLabelVisible: boolean;
}

const SidePanelActionItem = ({
  url,
  imageIcon,
  label,
  areLabelVisible,
}: SidePanelActionItemProps) => {
  return (
    <li>
      <Link href={url}>
        <div className="p-6 py-4 flex gap-4">
          {imageIcon}
          {areLabelVisible ? <p>{label}</p> : ""}
        </div>
      </Link>
    </li>
  );
};

export default SidePanelActionItem;
