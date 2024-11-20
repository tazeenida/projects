import React from 'react';

const Footer = () => {
  return (
    <footer className="footer-container">
      <div className="left-column">
        <a style={{ fontSize: '15px' }}><strong>MastodonHub</strong></a>
        <br />
        <a style={{ fontSize: '10px' }}>Your go-to event platform.</a>
      </div>
      <div className="right-column">
        <a href="../MastodonEvents/helpCenter.html" target="_blank" style={{ fontSize: '10px' }}>Help Center</a>
        <a href="../MastodonEvents/faq.html" target="_blank" style={{ fontSize: '10px' }}>FAQs</a>
        <a href="../MastodonEvents/supportTeam.html" target="_blank" style={{ fontSize: '10px' }}>Support Team</a>
        <a href="../MastodonEvents/userManual.html" target="_blank" style={{ fontSize: '10px' }}>User Manual</a>
        <a href="../MastodonEvents/contactSupport.html" target="_blank" style={{ fontSize: '10px' }}>Contact Support</a>
      </div>
    </footer>
  );
}

export default Footer;
