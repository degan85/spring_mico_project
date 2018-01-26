Vue.component('blog-articles', {
  template: `
   <div class='container'>
      <input v-model='searchQuery' class='search-box' placeholder='Search for articles...'>
      <div class='timeline' v-if='anyArticle()'>
        <div v-for='(dateWithArticles, date) in searchedArticles'>
          <p v-if='dateWithArticles.length > 0' class='date'>{{ date }} &nbsp; <button @click="addArticle(date)">+</button></p>
          <div v-for='article in dateWithArticles' class='article'>
            <span class='dot'></span>
            <p class='article-date'>{{ article.published_at }}</p>
            <h3><a :href="'#' + article.slug">{{ article.title }}</a></h3>
            <p>{{ article.teaser }}</p>
          </div>
        </div>
      </div>
      <p v-else>No articles found.</p>
    </div>
  `,
  props: ['datesArticles'],
  data() {
    return {
      searchQuery: ''
    }
  },
  computed: {
    searchedArticles() {
      var searchRegex = new RegExp(this.searchQuery, 'i');
      var searchedObj = {};

      if(this.searchQuery == '') {
        return this.datesArticles;
      }

      for(var date in this.datesArticles) {
        searchedObj[date] = this.datesArticles[date].filter((article) => {
          return searchRegex.test(article.title) ||
            searchRegex.test(article.teaser) ||
            searchRegex.test(article.published_at) ||
            searchRegex.test(date);
        });
      }
      return searchedObj;
    }
  },
  methods: {
    anyArticle() {
      return this.countAllArticles() ? true : false;
    },
    countAllArticles() {
      var count = 0;
      for(var date in this.searchedArticles) {
        count += this.searchedArticles[date].length;
      }
      return count;
    },
    addArticle(subject) {
      alert(subject);
    }
  }
});

new Vue({
  el: '#app',
  data: {
    newSubject: 'subject',
    newDate:'date',
    newSlug:'slug',
    newTeaser:'teaser',
    newTitle:'title',
    datesArticles: {
      '그림그리기': [
        {
          title: '그림을 그리기 시작',
          slug: '',
          teaser: '그리기 시작했다',
          published_at: '2016.03.02.'
        },
        {
          title: 'Four',
          slug: 'four',
          teaser: 'four',
          published_at: '15.09.2016.'
        }
      ],
      'April, 2016': [
        {
          title: 'Three',
          slug: 'three',
          teaser: 'three',
          published_at: '14.04.2016.'
        },
        {
          title: 'Two and a half',
          slug: 'two-and-a-half',
          teaser: 'two and a half',
          published_at: '02.04.2016.'
        }
      ],
      'December, 2015': [
        {
          title: 'Two',
          slug: 'two',
          teaser: 'two',
          published_at: '25.12.2015.'
        },
        {
          title: 'One',
          slug: 'one',
          teaser: 'one',
          published_at: '01.12.2015.'
        }
      ]
    }
  },
  methods: {
    addSubject() {
    	
    	let article = {
			title: this.newTitle,
			slug: this.newSlug,
			teaser: this.newTeaser,
			date: this.newDate,
			username : "degan"
    	};
    	
    	$.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/timeline/addSubject",
            data:  JSON.stringify(article),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
            	alert('success!!');
            	console.log(data);
            },
            error: function (e) {
            }
        });
    },
    changeSetting: function(index) {
      data.addArticle = data.settings[index];
    },
    showList() {
    	let username = {}
        username["username"] = 'degan';
    	
    	$.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/timeline/list",
            data: JSON.stringify(username),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
            	alert('success!!');
            	console.log(data);
            },
            error: function (e) {
            }
        });
    }
  }
});

