import { Link, useParams } from "react-router";

export interface NavigableListElement {
  id: number;
  title: string;
  subTitle: string;
}

interface NavigableListProps {
  title: string;
  urlPrefix: string;
  items: NavigableListElement[];
}

export const NavigableList = ({
  title,
  urlPrefix,
  items,
}: NavigableListProps) => {
  const { id: activeId } = useParams();
  return (
    <ul className="list bg-base-100 shadow-md">
      <li className="p-4 pb-2 text-xs opacity-60 tracking-wide">
        {title}: {items.length.toString()}
      </li>

      {items.length == 0 ? (
        <li className="list-row">
          <i>No results</i>
        </li>
      ) : (
        items.map(({ id, title, subTitle }) => (
          <li
            key={id}
            className={`flex ${id.toString() === activeId && "bg-gray-200"}`}
          >
            <Link
              to={`${urlPrefix}/${id}`}
              className="list-row rounded-none border-b border-gray-200 flex-1"
            >
              <div>
                {/* <img
                    className="size-10 rounded-box"
                    src="https://img.daisyui.com/images/profile/demo/1@94.webp"
                /> */}
                {/* <div><img className="size-10 rounded-box" src="https://cdn.brdmedia.com/tgpx/model-previews/janice-griffith-119.jpg?1747059392"/></div> */}
                <div className="text-4xl font-thin opacity-30 tabular-nums">
                  {id}
                </div>
              </div>
              <div>
                <div>{`${title}`}</div>
                <div className="text-xs uppercase font-semibold opacity-60">
                  {subTitle}
                </div>
              </div>
            </Link>
          </li>
        ))
      )}
    </ul>
  );
};
