export const Login = () => {
  return (
    <form className="card bg-base-200 shadow-xl p-6 space-y-4 w-96 mx-auto mt-10">
      <h2 className="text-xl font-bold">Chat App</h2>

      <div className="form-control">
        <label className="label">
          <span className="label-text">Username</span>
        </label>
        <input
          type="text"
          placeholder="Your name"
          className="input input-bordered"
          autoFocus
        />
      </div>

      <div className="form-control">
        <label className="label">
          <span className="label-text">Password</span>
        </label>
        <input
          type="password"
          placeholder="password"
          className="input input-bordered"
        />
      </div>

        <div className="flex justify-center space-x-2">
        <button type="submit" className="btn btn-primary">
            Register
        </button>
        <button type="submit" className="btn">
            Login
        </button>
        </div>
    </form>
    )
}