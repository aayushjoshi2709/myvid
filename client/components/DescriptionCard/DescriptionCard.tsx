import React from "react";

const DescriptionCard = (): React.ReactElement => {
  return (
    <div className="p-2 bg-gray-100 my-4 rounded-2xl px-4">
      <div className="flex gap-2">
        <p>
          <strong>300 views</strong>
        </p>
        <p>
          <strong>8 days ago</strong>
        </p>
      </div>
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Natus repellendus
      delectus repellat, vitae quaerat id voluptatem aut quasi quam corporis
      ipsa blanditiis harum! Illum, nesciunt repellat aliquam obcaecati ab
      voluptas.
    </div>
  );
};

export default DescriptionCard;
