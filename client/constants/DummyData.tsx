import VedioDetailsInterface from "@/common/interfaces/VedioDetails";

const DummyData: VedioDetailsInterface[] = [
  {
    id: "120",
    thumbnail: "/hq720.jpg",
    channel: {
      name: "My Youtube channel",
      logo: "/hq720.jpg",
      id: "234",
    },
    title:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum reprehenderit illum dolores eaque, nulla sed, nam at rem modi id beatae commodi sequi quas, vitae praesentium pariatur ipsam iusto quam?",
    createdAt: new Date().toISOString(),
    viewCount: 100,
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum reprehenderit illum dolores eaque, nulla sed, nam at rem modi id beatae commodi sequi quas, vitae praesentium pariatur ipsam iusto quam?",
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
    createdAt: new Date().toISOString(),
    viewCount: 5240,
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum reprehenderit illum dolores eaque, nulla sed, nam at rem modi id beatae commodi sequi quas, vitae praesentium pariatur ipsam iusto quam?",
  },
  {
    id: "122",
    thumbnail: "/hq720.jpg",
    channel: {
      name: "Dev Tutorials",
      logo: "/hq720.jpg",
      id: "235",
    },
    title: "React Performance Optimization Guide",
    createdAt: new Date().toISOString(),
    viewCount: 12500,
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum reprehenderit illum dolores eaque, nulla sed, nam at rem modi id beatae commodi sequi quas, vitae praesentium pariatur ipsam iusto quam?",
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
    createdAt: new Date().toISOString(),
    viewCount: 8340,
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum reprehenderit illum dolores eaque, nulla sed, nam at rem modi id beatae commodi sequi quas, vitae praesentium pariatur ipsam iusto quam?",
  },
  {
    id: "124",
    thumbnail: "/hq720.jpg",
    channel: {
      name: "Tech Academy",
      logo: "/hq720.jpg",
      id: "237",
    },
    title: "Full Stack Development with Next.js",
    createdAt: new Date().toISOString(),
    viewCount: 15600,
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum reprehenderit illum dolores eaque, nulla sed, nam at rem modi id beatae commodi sequi quas, vitae praesentium pariatur ipsam iusto quam?",
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
    createdAt: new Date().toISOString(),
    viewCount: 9870,
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum reprehenderit illum dolores eaque, nulla sed, nam at rem modi id beatae commodi sequi quas, vitae praesentium pariatur ipsam iusto quam?",
  },
];

export default DummyData;
