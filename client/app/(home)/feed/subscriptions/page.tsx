import VideoList from "@/components/VideoList/VideoList";

export default function SubscriptionFeed(): React.ReactElement {
  return (
    <div className="p-4 h-full overflow-scroll">
      <VideoList />
    </div>
  );
}
