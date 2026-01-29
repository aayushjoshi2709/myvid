export const AddComment = ({ videoId }: { videoId: string }) => {
  return (
    <div>
      <div className="w-full flex-row gap-4 mb-4">
        <form>
          <input
            type="text"
            placeholder="Type you comment here..."
            className="w-full p-2 border-b-2 focus:outline-none"
          />
          <div className="mt-2 flex gap-4 justify-end">
            <input type="button" value="Add Comment" />
            <input type="reset" value="Cancel" />
          </div>
        </form>
      </div>
    </div>
  );
};
