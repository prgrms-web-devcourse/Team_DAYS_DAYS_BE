package com.dayz.post.service;

import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.post.converter.PostConverter;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostRepository;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.dto.PostReadAllResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final PostConverter postConverter;

    private final AtelierRepository atelierRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(PostCreateRequest request) {
        return postRepository.save(postConverter.convertToPost(request)).getId();
    }

    @Transactional
    public CustomPageResponse getAllPostByAtelierId(Long atelierId, CustomPageRequest pageRequest) {
        PageRequest pageable = pageRequest.convertToPageRequest(Post.class);
//        Page<PostReadAllResult> postReadAllResults =
//                postRepository.findPostByAtelierIdAndUseFlagTrue(atelierId, pageable)
//                        .map(postConverter.convertToPostReadAllResult());
        Page<Post> foundedPosts = postRepository.findPostByAtelierIdAndUseFlagTrue(atelierId, pageable);
        Page<PostReadAllResult> postReadAllResults = foundedPosts.map(post -> postConverter.convertToPostReadAllResult(post));

        return CustomPageResponse.of(postReadAllResults);
    }

}
