<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="container-fluid pt-3">
    <h3 class="font-weight-bold">
        <s:message code="home.title" />
    </h3>

    <!-- Hiển thị múi giờ -->
    <p>Múi giờ hiện tại: <s:property value="#request.timeZone" /></p>

    <div class="language-selector">
        <a href="<c:url value='/home/changeLanguage?lang=en'/>">
            <img src="/images/flag_en.png" alt="English" />
        </a>
        <a href="<c:url value='/home/changeLanguage?lang=vi'/>">
            <img src="/images/flag_vi.png" alt="Tiếng Việt" />
        </a>
        <a href="<c:url value='/home/changeLanguage?lang=ja'/>">
            <img src="/images/flag_ja.png" alt="日本語" />
        </a>
        <a href="<c:url value='/home/changeLanguage?lang=zh'/>">
            <img src="/images/flag_zh.png" alt="中文" />
        </a>
    </div>

    <div class="row item">
        <!-- Card sản phẩm -->
        <div class="card" style="width: 20rem;">
            <img class="card-img-top" src="/images/dongHo9.jpg" width="100" height="270" alt="Card image cap">
            <div class="card-body mt-3">
                <div class="row m-auto">
                    <button class="btn btn-danger mr-2">
                        <i class="bi bi-heart-fill"></i>
                        <s:message code="home.like" />
                    </button>
                    <button class="btn btn-success">
                        <i class="bi bi-cart"></i>
                        <s:message code="home.cart" />
                    </button>
                </div>
            </div>
        </div>
        <!-- Thêm các thẻ card khác tương tự -->
    </div>
</div>
