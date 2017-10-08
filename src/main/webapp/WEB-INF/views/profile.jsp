<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Profile</title>

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/bootstrap/css/profile.css"/>" rel="stylesheet">

</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="<c:url value="/"/>">Home</a>
    <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/profile"/>">Profile <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/profile/settings"/>">Settings</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/categories"/>">Categories</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/help"/>">Help</a>
            </li>
        </ul>
        <form class="form-inline mt-2 mt-md-0 mr-5" action="<c:url value="/category"/>">
            <input class="form-control mr-sm-2" name="id" title="Category id" type="text" placeholder="Put category id"
                   aria-label="Put category id">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search category</button>
        </form>
        <form class="form-inline mt-2 mt-md-0 ml-5" action="<c:url value="/product"/>">
            <input class="form-control mr-sm-2" name="id" title="id" type="text" placeholder="Put product id"
                   aria-label="Put product id">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search product</button>
        </form>
    </div>
</nav>
<div class="container">
    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
    <div class="mail-box">
        <aside class="sm-side">
            <div class="user-head">
                <a class="inbox-avatar" href="javascript:;">
                    <img src="<c:url value="/userimages?username=${user.name}"/>" alt=""
                         height="60" width="64"/>
                </a>
                <div class="user-name">
                    <h5><a href="#">${user.name}</a></h5>
                    <span><a href="#">${user.email}</a></span>
                </div>
                <a class="mail-dropdown pull-right" href="javascript:">
                    <i class="fa fa-chevron-down"></i>
                </a>
            </div>
            <div class="inbox-body">
                <p>
                <form class="logout" method="get" action="<c:url value="/"/>">
                    <button class="btn btn-send" name="logout">Logout</button>
                </form>
                </p>
                <a href="<c:url value="/profile"/>" data-toggle="modal" title="Compose"
                   class="btn btn-compose">Refresh</a>
                <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal"
                     class="modal fade" style="display: none;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                <h4 class="modal-title">Compose</h4>
                            </div>
                            <div class="modal-body">
                                <form role="form" class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">To</label>
                                        <div class="col-lg-10">
                                            <input type="text" placeholder="" id="inputEmail1" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Cc / Bcc</label>
                                        <div class="col-lg-10">
                                            <input type="text" placeholder="" id="cc" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Subject</label>
                                        <div class="col-lg-10">
                                            <input type="text" placeholder="" id="inputPassword1" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Message</label>
                                        <div class="col-lg-10">
                                            <textarea rows="10" cols="30" class="form-control" id="" name=""></textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-lg-offset-2 col-lg-10">
                                                      <span class="btn green fileinput-button">
                                                        <i class="fa fa-plus fa fa-white"></i>
                                                        <span>Attachment</span>
                                                        <input type="file" name="files[]" multiple="">
                                                      </span>
                                            <button class="btn btn-send" type="submit">Send</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </div>
            <ul class="inbox-nav inbox-divider">
                <li class="active">
                    <a href="#"><i class="fa fa-inbox"></i> Inbox <span
                            class="label label-danger pull-right">2</span></a>

                </li>
                <li>
                    <a href="#"><i class="fa fa-envelope-o"></i> Sent Mail</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-bookmark-o"></i> Important</a>
                </li>
                <li>
                    <a href="#"><i class=" fa fa-external-link"></i> Drafts <span
                            class="label label-info pull-right">30</span></a>
                </li>
                <li>
                    <a href="#"><i class=" fa fa-trash-o"></i> Trash</a>
                </li>
            </ul>
            <ul class="nav nav-pills nav-stacked labels-info inbox-divider">
                <li><h4>Labels</h4></li>
                <li><a href="#"> <i class=" fa fa-sign-blank text-danger"></i> Work </a></li>
                <li><a href="#"> <i class=" fa fa-sign-blank text-success"></i> Design </a></li>
                <li><a href="#"> <i class=" fa fa-sign-blank text-info "></i> Family </a>
                </li>
                <li><a href="#"> <i class=" fa fa-sign-blank text-warning "></i> Friends </a>
                </li>
                <li><a href="#"> <i class=" fa fa-sign-blank text-primary "></i> Office </a>
                </li>
            </ul>

            <div class="inbox-body text-center">
                <div class="btn-group">
                    <a class="btn mini btn-primary" href="javascript:;">
                        <i class="fa fa-plus"></i>
                    </a>
                </div>
                <div class="btn-group">
                    <a class="btn mini btn-success" href="javascript:;">
                        <i class="fa fa-phone"></i>
                    </a>
                </div>
                <div class="btn-group">
                    <a class="btn mini btn-info" href="javascript:;">
                        <i class="fa fa-cog"></i>
                    </a>
                </div>
            </div>
            <div class="imageupload panel panel-default">
                <form method="post" action="<c:url value="/profile/imageupload"/>" enctype="multipart/form-data">
                    <input type="hidden" name="userName" value="${user.name}"/>
                    <div class="panel-heading clearfix">
                        <h3 class="panel-title pull-left">Upload Image</h3>
                        <div class="btn-group pull-right">
                            <button type="button" class="btn btn-default active">File</button>
                            <button type="button" class="btn btn-default">URL</button>
                        </div>
                    </div>
                    <div class="file-tab panel-body">
                        <label class="btn btn-default btn-file">
                            <span>Browse</span>
                            <input type="file" name="image">
                        </label>
                        <button type="button" class="btn btn-default">Remove</button>
                    </div>
                    <div class="url-tab panel-body">
                        <div class="input-group">
                            <input type="url" class="form-control hasclear" placeholder="Image URL">
                            <button type="button" class="btn btn-default">Submit</button>
                        </div>
                        <input type="hidden" name="image" multiple>
                        <button type="button" class="btn btn-default">Remove</button>
                    </div>
                    <p class="text-center">
                        <input class="btn btn-default btn-send" type="submit" value="Upload"/>
                    </p>
                </form>
            </div>
        </aside>
        <aside class="lg-side">
            <div class="inbox-head">
                <h3>Message box</h3>
                <form action="#" class="pull-right position">
                    <div class="input-append">
                        <input type="text" class="sr-input" placeholder="Search Message">
                        <button class="btn sr-btn" type="button"><i class="fa fa-search"></i></button>
                    </div>
                </form>
            </div>
            <div class="inbox-body">
                <div class="mail-option">
                    <div class="chk-all">
                        <input type="checkbox" class="mail-checkbox mail-group-checkbox">
                        <div class="btn-group">
                            <a data-toggle="dropdown" href="#" class="btn mini all" aria-expanded="false">
                                All
                                <i class="fa fa-angle-down "></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#"> None</a></li>
                                <li><a href="#"> Read</a></li>
                                <li><a href="#"> Unread</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="btn-group">
                        <a data-original-title="Refresh" data-placement="top" data-toggle="dropdown" href="#"
                           class="btn mini tooltips">
                            <i class=" fa fa-refresh"></i>
                        </a>
                    </div>
                    <div class="btn-group hidden-phone">
                        <a data-toggle="dropdown" href="#" class="btn mini blue" aria-expanded="false">
                            More
                            <i class="fa fa-angle-down "></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><i class="fa fa-pencil"></i> Mark as Read</a></li>
                            <li><a href="#"><i class="fa fa-ban"></i> Spam</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><i class="fa fa-trash-o"></i> Delete</a></li>
                        </ul>
                    </div>
                    <div class="btn-group">
                        <a data-toggle="dropdown" href="#" class="btn mini blue">
                            Move to
                            <i class="fa fa-angle-down "></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><i class="fa fa-pencil"></i> Mark as Read</a></li>
                            <li><a href="#"><i class="fa fa-ban"></i> Spam</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><i class="fa fa-trash-o"></i> Delete</a></li>
                        </ul>
                    </div>

                    <ul class="unstyled inbox-pagination">
                        <li><span>1-50 of 234</span></li>
                        <li>
                            <a class="np-btn" href="#"><i class="fa fa-angle-left  pagination-left"></i></a>
                        </li>
                        <li>
                            <a class="np-btn" href="#"><i class="fa fa-angle-right pagination-right"></i></a>
                        </li>
                    </ul>
                </div>
                <table class="table table-inbox table-hover">
                    <tbody>
                    <tr class="unread">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message  dont-show">PHPClass</td>
                        <td class="view-message ">Added a new class: Login Class Fast Site</td>
                        <td class="view-message  inbox-small-cells"><i class="fa fa-paperclip"></i></td>
                        <td class="view-message  text-right">9:27 AM</td>
                    </tr>
                    <tr class="unread">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">Google Webmaster</td>
                        <td class="view-message">Improve the search presence of WebSite</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">March 15</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">JW Player</td>
                        <td class="view-message">Last Chance: Upgrade to Pro for</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">March 15</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">Tim Reid, S P N</td>
                        <td class="view-message">Boost Your Website Traffic</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">April 01</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star inbox-started"></i></td>
                        <td class="view-message dont-show">Freelancer.com <span class="label label-danger pull-right">urgent</span>
                        </td>
                        <td class="view-message">Stop wasting your visitors</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">May 23</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star inbox-started"></i></td>
                        <td class="view-message dont-show">WOW Slider</td>
                        <td class="view-message">New WOW Slider v7.8 - 67% off</td>
                        <td class="view-message inbox-small-cells"><i class="fa fa-paperclip"></i></td>
                        <td class="view-message text-right">March 14</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star inbox-started"></i></td>
                        <td class="view-message dont-show">LinkedIn Pulse</td>
                        <td class="view-message">The One Sign Your Co-Worker Will Stab</td>
                        <td class="view-message inbox-small-cells"><i class="fa fa-paperclip"></i></td>
                        <td class="view-message text-right">Feb 19</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">Drupal Community<span class="label label-success pull-right">megazine</span>
                        </td>
                        <td class="view-message view-message">Welcome to the Drupal Community</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">March 04</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">Facebook</td>
                        <td class="view-message view-message">Somebody requested a new password</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">June 13</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">Skype <span class="label label-info pull-right">family</span>
                        </td>
                        <td class="view-message view-message">Password successfully changed</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">March 24</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star inbox-started"></i></td>
                        <td class="view-message dont-show">Google+</td>
                        <td class="view-message">alireza, do you know</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">March 09</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star inbox-started"></i></td>
                        <td class="dont-show">Zoosk</td>
                        <td class="view-message">7 new singles we think you'll like</td>
                        <td class="view-message inbox-small-cells"><i class="fa fa-paperclip"></i></td>
                        <td class="view-message text-right">May 14</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">LinkedIn</td>
                        <td class="view-message">Alireza: Nokia Networks, System Group and</td>
                        <td class="view-message inbox-small-cells"><i class="fa fa-paperclip"></i></td>
                        <td class="view-message text-right">February 25</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="dont-show">Facebook</td>
                        <td class="view-message view-message">Your account was recently logged into</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">March 14</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">Twitter</td>
                        <td class="view-message">Your Twitter password has been changed</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">April 07</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">InternetSeer Website Monitoring</td>
                        <td class="view-message">http://golddesigner.org/ Performance Report</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">July 14</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star inbox-started"></i></td>
                        <td class="view-message dont-show">AddMe.com</td>
                        <td class="view-message">Submit Your Website to the AddMe Business Directory</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">August 10</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">Terri Rexer, S P N</td>
                        <td class="view-message view-message">Forget Google AdWords: Un-Limited Clicks fo</td>
                        <td class="view-message inbox-small-cells"><i class="fa fa-paperclip"></i></td>
                        <td class="view-message text-right">April 14</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">Bertina</td>
                        <td class="view-message">IMPORTANT: Don't lose your domains!</td>
                        <td class="view-message inbox-small-cells"><i class="fa fa-paperclip"></i></td>
                        <td class="view-message text-right">June 16</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star inbox-started"></i></td>
                        <td class="view-message dont-show">Laura Gaffin, S P N</td>
                        <td class="view-message">Your Website On Google (Higher Rankings Are Better)</td>
                        <td class="view-message inbox-small-cells"></td>
                        <td class="view-message text-right">August 10</td>
                    </tr>
                    <tr class="">
                        <td class="inbox-small-cells">
                            <input type="checkbox" class="mail-checkbox">
                        </td>
                        <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                        <td class="view-message dont-show">Facebook</td>
                        <td class="view-message view-message">Alireza Zare Login faild</td>
                        <td class="view-message inbox-small-cells"><i class="fa fa-paperclip"></i></td>
                        <td class="view-message text-right">feb 14</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </aside>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="/resources/bootstrap/js/popper.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap-imageupload.js"></script>
<script src="/resources/bootstrap/js/profile.js"></script>
</body>
</html>
