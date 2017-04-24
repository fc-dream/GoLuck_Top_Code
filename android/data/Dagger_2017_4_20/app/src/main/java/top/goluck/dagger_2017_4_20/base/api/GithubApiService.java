package top.goluck.dagger_2017_4_20.base.api;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import top.goluck.dagger_2017_4_20.model.UserResponse;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public interface GithubApiService {
    @GET("/users/{username}")
    Observable<UserResponse> getUser(
            @Path("username") String username
    );
}
