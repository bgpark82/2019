import React from 'react';
import styled from 'styled-components';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import palette from '../../lib/style/palette';
import Button from '../common/Button';

const AuthFormBlock = styled.div`
  h3 {
    margin: 0;
    color: ${palette.gray[8]};
    margin-bottom: 1rem;
  }
`;

const StyledInput = styled.input`
  border: none;
  outline: none;
  font-size: 1rem;
  border-bottom: 1px solid ${palette.gray[5]};
  padding-bottom: 0.5rem;
  width: 100%;

  &:focus {
    color: $oc-teal-7;
    border-bottom: 1px solid ${palette.gray[7]};
  }

  & + & {
    margin-top: 1rem;
  }
`;

const Footer = styled.div`
  margin-top: 2rem;
  text-align: center;
  a {
    color: ${palette.gray[6]};
    text-decoration: underline;
    &:hover {
      color: ${palette.gray[9]};
    }
  }
`;

const ButtonMarginTop = styled(Button)`
  margin-top: 1rem;
`;

const typeMap = { login: '로그인', register: '회원가입' };

export const AuthForm = ({ type }) => {
  const text = typeMap[type];
  return (
    <AuthFormBlock>
      <h3>{text}</h3>
      <form>
        <StyledInput
          placeholder="아이디"
          name="username"
          autoComplete="username"
        />
        <StyledInput
          placeholder="비밀번호"
          name="password"
          autoComplete="new-password"
          type="password"
        />
        {text === '회원가입' && (
          <StyledInput
            placeholder="비밀번호 확인"
            name="passwordConfirm"
            autoComplete="new-password"
            type="password"
          />
        )}
        <ButtonMarginTop fullWidth cyan>
          {text}
        </ButtonMarginTop>
      </form>
      <Footer>
        {text === '로그인' ? (
          <Link to="/register">회원가입</Link>
        ) : (
          <Link to="/login">로그인</Link>
        )}
      </Footer>
    </AuthFormBlock>
  );
};
