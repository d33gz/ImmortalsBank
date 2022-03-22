package revature.bank;
import io.javalin.http.Handler;
import revature.queries.Get;
import revature.queries.GetAll;
import revature.queries.Put;
import revature.queries.Post;

public class Handlers {
//	Outdated
//	public static Handler getClients = ctx -> {
//		ctx.result(GetAll.gettingAll());
//	};
	public static Handler getOneClient = ctx -> {
		int id = Integer.parseInt(ctx.pathParam("num"));
		ctx.result(Get.getClient(id));
	};
	public static Handler updateClientName = ctx -> {
		int id = Integer.parseInt(ctx.pathParam("num"));
		String name = ctx.pathParam("name");
		ctx.result(Put.updateClient(id, name));
	};
	public static Handler postClient = ctx -> {
		String name = ctx.pathParam("name");
		ctx.result(Post.postClient(name));
	};
}
