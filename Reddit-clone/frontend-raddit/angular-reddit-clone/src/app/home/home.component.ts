import { Component, OnInit } from '@angular/core';
import { PostService } from '../shared/post.service';
import { PostModel } from '../shared/post-model';
import {faArrowDown,faArrowUp, faComments} from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../auth/shared/auth.service'
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    posts: Array<PostModel> = [];
    isLoggedIn:boolean;

    constructor(private postService: PostService,private authService: AuthService) {
      this.postService.getAllPosts().subscribe(post => {
        this.posts = post;
      });
    }
  
    ngOnInit(): void {
      this.isLoggedIn = this.authService.isLoggedIn();

    }

}
