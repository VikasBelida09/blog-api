package com.example.blogapi.services.impl;

import com.example.blogapi.entities.Category;
import com.example.blogapi.entities.Post;
import com.example.blogapi.entities.PostResponse;
import com.example.blogapi.entities.User;
import com.example.blogapi.exceptions.CategoryNotFoundException;
import com.example.blogapi.exceptions.PostNotFoundException;
import com.example.blogapi.exceptions.UserNotFoundException;
import com.example.blogapi.payloads.PostDTO;
import com.example.blogapi.repositories.CategoryRepository;
import com.example.blogapi.repositories.PostRepository;
import com.example.blogapi.repositories.UserRepository;
import com.example.blogapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDto,Long userId,Long categoryId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException(categoryId));
        Post post=this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setUser(user);
        post.setCategory(category);
        return this.modelMapper.map(this.postRepository.save(post),PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDto) {
        Post post=this.postRepository.findById(postDto.getId()).orElseThrow(()->new PostNotFoundException(postDto.getId()));
        Category category=this.categoryRepository.findById(postDto.getCategory().getId()).orElseThrow(()->new CategoryNotFoundException(postDto.getCategory().getId()));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setCategory(category);
        return this.modelMapper.map(this.postRepository.save(post), PostDTO.class);
    }

    @Override
    public PostDTO findPostById(Long id) {
        Post post=this.postRepository.findById(id).orElseThrow(()->new PostNotFoundException(id));
        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public boolean deletePostById(Long id) {
        Post post=this.postRepository.findById(id).orElseThrow(()->new PostNotFoundException(id));
        this.postRepository.delete(post);
        return true;
    }

    @Override
    public PostResponse getAllPosts(Integer pageSize,Integer pageNumber,String sortBy,String sortDir) {
        List<Sort.Order>orderList=Arrays.stream(sortBy.split(",")).map(string -> new Sort.Order(sortDir.equalsIgnoreCase("desc")? Sort.Direction.DESC: Sort.Direction.ASC,string,false, Sort.NullHandling.NATIVE)).toList();
        Sort sort=Sort.by(orderList);
        Pageable pg= PageRequest.of(pageNumber,pageSize,sort);
        Page<Post>posts=this.postRepository.findAll(pg);
        List<PostDTO> postDTOList=posts.getContent().stream().map(p->this.modelMapper.map(p, PostDTO.class)).toList();
        PostResponse postResponse=new PostResponse();
        postResponse.setPosts(postDTOList);
        postResponse.setLastPage(posts.isLast());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setPageNumber(posts.getNumber());
        return postResponse;
    }

    @Override
    public List<PostDTO> searchByPostTitle(String title) {
        return this.postRepository.findByTitleContainingIgnoreCase(title).stream().map(post->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findPostsByUser(Long userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        List<Post> posts= this.postRepository.findByUser(user);
        return posts.stream().map(post->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findPostByCategory(Long categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException(categoryId));
        List<Post> posts=this.postRepository.findByCategory(category);
        return posts.stream().map(post->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
    }
}
