import { ExclamationTriangleIcon } from "@heroicons/react/24/outline";
import { centeredFlex } from "../shared/cssConstants";

interface ApiErrorViewProps {
  title: string;
  error: any;
}

export const ApiErrorView = ({ title, error }: ApiErrorViewProps) => {
  return (
    <div className={centeredFlex}>
      <ExclamationTriangleIcon className="size-10" />
      <label className="text-2xl font-bold">{title} could not be loaded</label>
      <div className="text-xs">{error.toString()}</div>
    </div>
  );
};
