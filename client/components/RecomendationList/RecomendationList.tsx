import VedioCard from "@/components/VedioCard/VedioCard";

const RecomendationList = () => {
  const data = [
    {
      id: "120",
      thumbnail: "/hq720.jpg",
      channelLogo: "/hq720.jpg",
      title: "This is the recon tool you need in 2006",
      channelName: "My Youtube channel",
      createdAt: "12/12/32",
      viewCount: 100,
    },
    {
      id: "121",
      thumbnail: "/hq720.jpg",
      channelLogo: "/hq720.jpg",
      title: "Advanced Web Development Techniques",
      channelName: "Code Masters",
      createdAt: "12/12/32",
      viewCount: 5240,
    },
    {
      id: "122",
      thumbnail: "/hq720.jpg",
      channelLogo: "/hq720.jpg",
      title: "React Performance Optimization Guide",
      channelName: "Dev Tutorials",
      createdAt: "12/12/32",
      viewCount: 12500,
    },
    {
      id: "123",
      thumbnail: "/hq720.jpg",
      channelLogo: "/hq720.jpg",
      title: "TypeScript Tips and Tricks",
      channelName: "Programming Insights",
      createdAt: "12/12/32",
      viewCount: 8340,
    },
    {
      id: "124",
      thumbnail: "/hq720.jpg",
      channelLogo: "/hq720.jpg",
      title: "Full Stack Development with Next.js",
      channelName: "Tech Academy",
      createdAt: "12/12/32",
      viewCount: 15600,
    },
    {
      id: "125",
      thumbnail: "/hq720.jpg",
      channelLogo: "/hq720.jpg",
      title: "CSS Grid and Flexbox Mastery",
      channelName: "Design Code",
      createdAt: "12/12/32",
      viewCount: 9870,
    },
  ];
  return (
    <div className="flex flex-col">
      {data.map((vedioData, idx) => {
        return (
          <VedioCard
            key={idx}
            thumbnailStyle={{
              size: "8vw",
              width: "100%",
              height: "100%",
            }}
            vedioInfoAlignment="horizontal"
            hideChannelLogo={true}
            {...vedioData}
          />
        );
      })}
    </div>
  );
};

export default RecomendationList;
