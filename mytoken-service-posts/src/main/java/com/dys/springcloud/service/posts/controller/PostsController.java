package com.dys.springcloud.service.posts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dys.springcloud.common.domain.TbPostsPost;
import com.dys.springcloud.common.dto.BaseResult;
import com.dys.springcloud.service.posts.service.PostsService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/v1/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    /**
     * 根据 ID 获取文章
     * @param postGuid
     * @return
     */
    @RequestMapping(value = "/{postGuid}", method = RequestMethod.GET)
    public BaseResult getPostsHandler(@PathVariable(value = "postGuid") String postGuid) {
    	System.out.println("-----------------postGuid------------- :"+postGuid);
        TbPostsPost tbPostsPost = new TbPostsPost();
        tbPostsPost.setPostGuid(postGuid);
        TbPostsPost selectByOne = postsService.selectByOne(tbPostsPost);
        if(selectByOne == null) {
			return BaseResult.ok("不存在该ID的文章！");
		}
		return BaseResult.ok(selectByOne);
    }

    /**
         * 保存文章
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult savePostsHandler(@RequestBody(required = true) TbPostsPost tbPostsPost, @RequestParam(required = true) String optsBy) {
    	Map<String, String> info = new HashMap<String, String>();
		if(tbPostsPost == null || StringUtils.isBlank(optsBy)) {
			info.put("message", "参数有误！");
			return BaseResult.notOk(info);
		}
		int result = postsService.save(tbPostsPost, optsBy);
		if(result > 0) {
			return BaseResult.ok("保存文章成功！");
		}
		info.put("message", "保存文章失败！");
		return BaseResult.notOk(info);
    }

    /**
         * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult pagePostsHandler(@PathVariable(required = true) int pageNum, @PathVariable(required = true) int pageSize){
        PageInfo<TbPostsPost> pageInfo = postsService.page(pageNum, pageSize, new TbPostsPost());
        // 分页后的结果集
        List<TbPostsPost> list = pageInfo.getList();
        // 封装 Cursor 对象
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }
}
