import React from 'react';
import styled from 'styled-components';
import Button from './Button';
import Responsive from './Responsive';

const HeaderBlock = styled.div`
  position: fixed;
  width: 100%;
  background: white;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.08);
`;
const Wrapper = styled(Responsive)`
  height: 4rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .logo {
    font-size: 1.125rem;
    font-weight: 800;
    letter-spacing: 1px;
  }
  .right {
    display: flex;
    align-items: center;
  }
`;
const Spacer = styled.div`
  height: 4rem;
`;

const Header = () => {
  return (
    <>
      <HeaderBlock>
        <Wrapper>
          <div className="logo">REACTERS</div>
          <div className="right">
            <Button>로그인</Button>
          </div>
        </Wrapper>
      </HeaderBlock>
      <Spacer />
    </>
  );
};
export default Header;
