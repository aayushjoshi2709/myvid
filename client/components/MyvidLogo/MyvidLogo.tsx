interface MyVidLogoProps {
  width: number | string;
  height: number | string;
}

const MyVidLogo = ({ width = 260, height = 80 }: MyVidLogoProps) => {
  return (
    <svg
      width={width}
      height={height}
      viewBox="0 0 260 80"
      xmlns="http://www.w3.org/2000/svg"
      role="img"
      aria-label="myvid logo"
    >
      <defs>
        <linearGradient id="myvid-rainbow" x1="0%" y1="0%" x2="100%" y2="100%">
          <stop offset="0%" stopColor="#ff4d8d" />
          <stop offset="30%" stopColor="#ff9f1c" />
          <stop offset="60%" stopColor="#2ec4b6" />
          <stop offset="100%" stopColor="#4d96ff" />
        </linearGradient>

        <filter id="myvid-shadow" x="-20%" y="-20%" width="140%" height="140%">
          <feDropShadow
            dx="0"
            dy="4"
            stdDeviation="6"
            floodColor="#000"
            floodOpacity="0.25"
          />
        </filter>
      </defs>

      {/* Play button */}
      <g filter="url(#myvid-shadow)">
        <path
          d="
            M20 10
            Q10 10 10 20
            V60
            Q10 70 20 70
            H60
            Q70 70 70 60
            V20
            Q70 10 60 10
            Z
          "
          fill="url(#myvid-rainbow)"
        />

        <polygon points="32,25 32,55 55,40" fill="#ffffff" />
      </g>

      {/* Brand text */}
      <text
        x="90"
        y="52"
        fontFamily="Inter, system-ui, -apple-system, sans-serif"
        fontSize="36"
        fontWeight="800"
        fill="#0f172a"
      >
        myvid
      </text>
    </svg>
  );
};

export default MyVidLogo;
