<?cs def:custom_masthead() ?>
  <a name="top"></a>
  <!-- Header -->
  <div id="header-wrapper">
    <div class="dac-header <?cs if:ndk ?>dac-ndk<?cs /if ?>" id="header">
      <div class="dac-header-inner">
        <a class="dac-nav-toggle" data-dac-toggle-nav href="javascript:;" title="Open navigation">
          <span class="dac-nav-hamburger">
            <span class="dac-nav-hamburger-top"></span>
            <span class="dac-nav-hamburger-mid"></span>
            <span class="dac-nav-hamburger-bot"></span>
          </span>
        </a>
        <?cs if:ndk ?><a class="dac-header-logo" style="width:144px;" href="<?cs var:toroot ?>reference/index.html">
          <img class="dac-header-logo-image" src="<?cs var:toroot ?>assets/images/android_logo.png"
              srcset="<?cs var:toroot ?>assets/images/android_logo@2x.png 2x"
              width="32" height="36" alt="Android" /> NDK
          </a><?cs else ?><a class="dac-header-logo" href="<?cs var:toroot ?>reference/index.html">
          <img class="dac-header-logo-image" src="<?cs var:toroot ?>assets/images/android_logo.png"
              srcset="<?cs var:toroot ?>assets/images/android_logo@2x.png 2x"
              width="32" height="36" alt="Android" /> Developers
          </a><?cs /if ?>

        <?cs if:ndk
        ?><?cs else
        ?><?cs
        #
        # For the reference only docs, include just one tab
        #
        ?><?cs if:referenceonly
          ?><ul class="dac-header-tabs">
            <li><a href="<?cs var:toroot ?>reference/packages.html" class="dac-header-tab"><?cs
              if:sdk.preview
                ?>Android <?cs var:sdk.codename ?>
                  Preview <?cs var:sdk.preview.version ?><?cs
              else
                ?>Android <?cs var:sdk.version ?>
                  r<?cs var:sdk.rel.id ?><?cs
              /if ?></a>
            </li>
          </ul>
        <?cs else ?><?cs
        #
        # End reference only docs, now the online DAC tabs...
        #
        ?><?cs
        /if ?><?cs
        #
        # End if/else reference only docs
        #
        ?><?cs
        /if ?><?cs # end if/else ndk ?>

        <?cs if:ndk ?><?cs else ?><?cs /if ?><?cs

        # ADD SEARCH AND MENU ?><?cs
        if:!ndk ?><?cs
          if:!referenceonly ?><?cs
          /if ?><?cs
        /if ?>
      </div><!-- end header-wrap.wrap -->
    </div><!-- end header -->
  </div> <!--end header-wrapper -->

  <?cs if:ndk ?>
  <!-- NDK Navigation-->
  <nav class="dac-nav">
    <div class="dac-nav-dimmer" data-dac-toggle-nav></div>

    <div class="dac-nav-sidebar" data-swap data-dynamic="false" data-transition-speed="300" data-dac-nav>
                   <div data-swap-container>
        <?cs call:custom_left_nav() ?>
      <ul id="dac-main-navigation" class="dac-nav-list dac-swap-section dac-left dac-no-anim">
      <li class="dac-nav-item guides">
        <a class="dac-nav-link" href="<?cs var:toroot ?>ndk/guides/index.html"
           zh-tw-lang="API 指南"
           zh-cn-lang="API 指南"
           ru-lang="Руководства по API"
           ko-lang="API 가이드"
           ja-lang="API ガイド"
           es-lang="Guías de la API">Guides</a>
      </li>
      <li class="dac-nav-item reference">
        <a class="dac-nav-link" href="<?cs var:toroot ?>ndk/reference/index.html"
           zh-tw-lang="參考資源"
           zh-cn-lang="参考"
           ru-lang="Справочник"
           ko-lang="참조문서"
           ja-lang="リファレンス"
           es-lang="Referencia">Reference</a>
      </li>
      <li class="dac-nav-item samples">
        <a class="dac-nav-link" href="<?cs var:toroot ?>ndk/samples/index.html"
           >Samples</a>
      </li>
      <li class="dac-nav-item downloads">
        <a class="dac-nav-link" href="<?cs var:toroot ?>ndk/downloads/index.html"
           >Downloads</a>
      </li>
      </ul>
    </div>
                       </div>
  </nav>
  <!-- end NDK navigation-->

  <?cs else ?>
  <!-- Navigation-->
  <nav class="dac-nav">
    <div class="dac-nav-dimmer" data-dac-toggle-nav></div>

    <div class="dac-nav-sidebar" data-swap data-dynamic="false" data-transition-speed="300" data-dac-nav>
      <div>
        <?cs call:custom_left_nav() ?>
      </div>
    </div>
  </nav>
  <!-- end navigation-->
  <?cs /if ?>

<!-- Nav Setup -->
<script>$('[data-dac-nav]').dacNav();</script>

<?cs
/def ?><?cs # end custom_masthead() ?><?cs

def:toast() ?><?cs

# (UN)COMMENT TO TOGGLE VISIBILITY

  <div class="dac-toast-group">
    <div class="dac-toast" data-toast>
      <div class="dac-toast-wrap">
        This is a demo notification <a href="#">Learn more</a>.
      </div>
    </div>
  </div>

?><?cs
/def ?>