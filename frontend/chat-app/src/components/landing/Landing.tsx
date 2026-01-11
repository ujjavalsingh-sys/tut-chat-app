import { Link } from "react-router";

export const Landing = () => {
  return (
    <div>
      <Link className="btn" to="/login">
        Login
      </Link>
      <Link className="btn" to="/dashboard">
        Dashboard
      </Link>
    </div>
  );
};
