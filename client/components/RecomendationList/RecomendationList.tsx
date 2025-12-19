import VideoCard from "@/components/VideoCard/VideoCard";

const RecomendationList = (): React.ReactElement => {
  const data = [
    {
      id: "120",
      thumbnail: "/hq720.jpg",
      channel: {
        name: "My Youtube channel",
        logo: "/hq720.jpg",
        id: "234",
      },
      title: "This is the recon tool you need in 2006",
      createdAt: "12/12/32",
      viewCount: 100,
    },
    {
      id: "121",
      thumbnail: "/hq720.jpg",
      channel: {
        name: "Code Masters",
        logo: "/hq720.jpg",
        id: "234",
      },
      title: "Advanced Web Development Techniques",
      createdAt: "12/12/32",
      viewCount: 5240,
    },
    {
      id: "122",
      thumbnail: "/hq720.jpg",
      channelLogo: "/hq720.jpg",
      channel: {
        name: "Dev Tutorials",
        logo: "/hq720.jpg",
        id: "235",
      },
      title: "React Performance Optimization Guide",
      createdAt: "12/12/32",
      viewCount: 12500,
    },
    {
      id: "123",
      thumbnail: "/hq720.jpg",
      channel: {
        name: "Programming Insights",
        logo: "/hq720.jpg",
        id: "236",
      },
      title: "TypeScript Tips and Tricks",
      createdAt: "12/12/32",
      viewCount: 8340,
    },
    {
      id: "124",
      thumbnail: "/hq720.jpg",
      channel: {
        name: "Tech Academy",
        logo: "/hq720.jpg",
        id: "237",
      },
      channelLogo: "/hq720.jpg",
      title: "Full Stack Development with Next.js",
      createdAt: "12/12/32",
      viewCount: 15600,
    },
    {
      id: "125",
      thumbnail: "/hq720.jpg",
      channel: {
        name: "Design Code",
        logo: "/hq720.jpg",
        id: "238",
      },
      title: "CSS Grid and Flexbox Mastery",
      channelName: "Design Code",
      createdAt: "12/12/32",
      viewCount: 9870,
    },
  ];
  return (
    <div className="flex flex-col">
      {data.map((videoData, idx) => {
        return (
          <VideoCard
            key={idx}
            thumbnailStyle={{
              size: "8vw",
              width: "100%",
              height: "100%",
            }}
            videoInfoAlignment="horizontal"
            hideChannelLogo={true}
            {...videoData}
          />
        );
      })}
    </div>
  );
};

export default RecomendationList;
