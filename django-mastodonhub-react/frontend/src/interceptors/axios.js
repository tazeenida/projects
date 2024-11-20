import axios from "axios";

const token = localStorage.getItem("access_token");
axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;

const axiosInstance = axios.create({
  baseURL: "https://django-mastodonhub-react-1.onrender.com",
  headers: {
    "Content-Type": "application/json",
  },
});

let refresh = false;

axiosInstance.interceptors.response.use(
  (response) => response,
  async (error) => {
    if (error.response.status === 401 && !refresh) {
      refresh = true;

      const refreshToken = localStorage.getItem("refresh_token");
      if (refreshToken) {
        const refreshResponse = await axios.post(
          { refresh: refreshToken },
          { headers: { "Content-Type": "application/json" } }
        );

        if (refreshResponse.status === 200) {
          const newAccessToken = refreshResponse.data.access;

          localStorage.setItem("access_token", newAccessToken);

          error.config.headers["Authorization"] = `Bearer ${newAccessToken}`;
          refresh = false; 
          return axiosInstance(error.config);
        }
      }

      localStorage.clear();
      window.location.href = "/login";
    }

    refresh = false;
    return Promise.reject(error);
  }
);

export default axiosInstance;
