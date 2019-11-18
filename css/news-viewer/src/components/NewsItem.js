import React from 'react';
import styled from 'styled-components';

const NewsItemBlock = styled.div`
  display: flex;
  .thumbnail {
    margin-right: 1rem;
    img {
      display: block;
      width: 168px;
      height: 100px;
      object-fit: cover;
    }
  }
  .content {
    h2 {
      margin: 0;
      a {
        color: black;
      }
    }
    p {
      margin: 0;
      line-height: 1.5;
      margin-top: 0.6rem;
      white-space: normal;
    }
  }
  & + & {
    margin-top: 3rem;
  }
`;

export const NewsItem = ({ article }) => {
  const { title, description, url, urlToImage } = article;
  return (
    <NewsItemBlock>
      {urlToImage && (
        <div className="thumbnail">
          <a href="{url}" target="_blank" rel="noopener noreferrer">
            <img src={urlToImage} alt="" />
          </a>
        </div>
      )}
      <div className="contents">
        <h2>
          <a href={url}>{title}</a>
        </h2>
        <p>{description}</p>
      </div>
    </NewsItemBlock>
  );
};
