<%@page session="false"%><%--
  Copyright 1997-2008 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  Flash component

--%><%@ page import="java.net.URLEncoder,
                     java.util.UUID,
                     org.apache.sling.api.resource.ResourceUtil,
                     com.day.cq.wcm.api.components.DropTarget,
                     com.day.cq.wcm.api.WCMMode,
                     com.day.cq.wcm.foundation.Download,
                     com.day.cq.wcm.foundation.Placeholder,
                     com.day.text.Text" %><%
%><%@include file="/libs/foundation/global.jsp"%><%!

    private String getImgUrl(Resource resource, ValueMap properties, String name) {
        String imgUrl = null;
        String imgRef = properties.get("imageReference", null);
        Resource img = resource.getResourceResolver().resolve(resource.getPath()+"/image/jcr:content");
        if (imgRef != null) {
            imgUrl = imgRef;
        } else if (img != null) {
            Node imgNode = img.adaptTo(Node.class);
            String ext = "gif";
            try {
                String mimeType = imgNode.getProperty("jcr:mimeType").getString();
                ext = mimeType.substring(mimeType.indexOf("/") + 1);
            } catch (Exception e) {}
            imgUrl = img.getPath() + ".res/" + name + "." + ext;
        }
        return imgUrl;
    }
    
    private boolean hasImage(Resource resource, ValueMap properties){
        return (properties.get("imageReference", null)!=null) || !ResourceUtil.isNonExistingResource(resource.getResourceResolver().resolve(resource.getPath()+"/image/jcr:content"));
    }

%><%

    String name = currentNode.getName();
    String xiUrl = "/etc/clientlibs/foundation/swfobject/swf/expressInstall.swf";
    String flashUrl = currentNode.getPath() + "/file.res/" + name + ".swf";
    String width = properties.get("width", "100%");
    String height = properties.get("height", "160");
    String flashVersion = properties.get("flashVersion", "9.0.0");
    String flashVars = "{backgroundColor:\"" + xssAPI.encodeForJSString(properties.get("bgColor", "ffffff")) + "\"}";
    String menu = properties.get("menu", "");
    menu = "show".equals(menu) ? "true" : "false";
    String params = "{menu:\"" + menu + "\",wmode:\"" + xssAPI.encodeForJSString(properties.get("wmode", "opaque")) + "\"}";

    String[] attrs = properties.get("attrs", "").split(",");
    String jsAttrs = "{";
    for (int i = 0; i < attrs.length; i++) {
        // position of first colon
        int pos = attrs[i].indexOf(":");
        if (pos == -1) continue; // skip none name/value pairs
        String key = xssAPI.getValidJSToken(attrs[i].substring(0, pos), "XSS");
        String value = xssAPI.encodeForJSString(attrs[i].substring(pos+1));
        jsAttrs += (jsAttrs.length() > 1 ? ", " : "") + key + ":" + "\"" + value + "\"";
    }
    jsAttrs += "}";

    //drop target css class = dd prefix + name of the drop target in the edit config
    String ddClassName = DropTarget.CSS_CLASS_PREFIX + "flash";

    Download dld = new Download(resource);

    String cls = " class=\"" + ddClassName + (WCMMode.fromRequest(request) == WCMMode.EDIT && !dld.hasContent() ? " cq-flash-placeholder" : "") + "\"";
    String style = WCMMode.fromRequest(request) == WCMMode.EDIT && !dld.hasContent() ? " style=\"width:100%\"" : "";

    boolean hasImage = hasImage(resource,properties);
    if(!dld.hasContent() && !hasImage){
        height = "0";
        String clsTouch = Placeholder.isAuthoringUIModeTouch(request) ? "cq-placeholder cq-Overlay cq-Overlay--placeholder " : "";
        String clsPlaceholder = "cq-flash-placeholder " + ddClassName;
        String componentTitle = Placeholder.getComponentTitle(component);
        String defaultPlaceholder = "<div " + "class=\"" + clsPlaceholder + "\" title=\"" + componentTitle + "\" data-text=\"" + componentTitle + "\"></div>";

        String placeholder = Placeholder.getDefaultPlaceholder(slingRequest, component, defaultPlaceholder, clsTouch, clsPlaceholder);
        %><%= placeholder %><%
    }

    String imgUrl = getImgUrl(resource, properties, name);
    if (imgUrl != null) {
        imgUrl = URLEncoder.encode(imgUrl, "UTF-8");
        imgUrl = imgUrl.replaceAll("%2F", "/");
        String styleWidth = width.endsWith("%") ? width : width + "px";
        String styleHeight = height.endsWith("%") ? height : height + "px";
        style = " style=\"width:" + xssAPI.encodeForHTMLAttr(styleWidth) + ";" +
                "height:" + xssAPI.encodeForHTMLAttr(styleHeight) + ";" +
                (hasImage ? "background:transparent url('" + xssAPI.getValidHref(request.getContextPath() + imgUrl) + "') no-repeat center center;" : "") +
                "\"";
    }

    String id = UUID.nameUUIDFromBytes(resource.getPath().getBytes("UTF-8")).toString() + "_swf";
    String flashInfo="";
    String altContentStyle="";
    if( WCMMode.fromRequest(request) == WCMMode.EDIT){
        flashInfo="&nbsp;";
    } else if( !hasImage ) {
        String showAltComponentName = request.getParameter("showAltComponentName");
        if(showAltComponentName!=null && showAltComponentName.equalsIgnoreCase("true")) {
            flashInfo="<h2 class=\"cq-paragraphreference-thumbnail-text\">" + xssAPI.encodeForHTML(resource.getName()) + "</h2>";
            altContentStyle = "style = visibility:visible; ";
        }
    }

%><div <%= cls %><%= style %>><div id="<%= xssAPI.encodeForHTMLAttr(id) %>" <%= altContentStyle %> >
        <%=flashInfo %>
    </div></div><%

    if (dld.hasContent()) {
        cls = ""; // remove empty representation
        String href = dld.getHref();
        if ("".equals(href)) {
            href = flashUrl;
        }
        href = Text.escape(href, '%', true);
        %>
        <cq:includeClientLib js="cq.swfobject" />
        <script type="text/javascript">
            if( window.CQ_swfobject) CQ_swfobject.embedSWF(CQ.shared.HTTP.getXhrHookedURL("<%= xssAPI.getValidHref(request.getContextPath() + href) %>"), "<%= xssAPI.encodeForJSString(id) %>", "<%= xssAPI.encodeForJSString(width) %>", "<%= xssAPI.encodeForJSString(height) %>", "<%= xssAPI.encodeForJSString(flashVersion) %>", CQ.shared.HTTP.getXhrHookedURL("<%= request.getContextPath() + xiUrl %>"), <%= flashVars %>, <%= params %>, <%= jsAttrs %>);
        </script>
        <%
    }
%>
