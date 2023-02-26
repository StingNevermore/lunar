<template>
  <div :class="`${prefixCls}`">
    <div :class="`${prefixCls}__links`">
      <a @click="openWindow(GONGAN_URL)">冀公网安备 13112302000224号</a>

      <a style="margin-left: 10px" @click="openWindow(BEIAN_URL)">{{ t('layout.footer.icp') }}</a>
    </div>
  </div>
</template>
<script setup>
  import { openWindow } from '/@/utils';
  import { BEIAN_URL, GONGAN_URL } from '/@/settings/siteSetting';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useDesign } from '/@/hooks/web/useDesign';

  const { t } = useI18n();
  const { prefixCls } = useDesign('layout-footer');
</script>
<style lang="less" scoped>
  @prefix-cls: ~'@{namespace}-layout-footer';

  @normal-color: rgba(0, 0, 0, 0.45);

  @hover-color: rgba(0, 0, 0, 0.85);

  .@{prefix-cls} {
    color: @normal-color;

    &__links {
      a {
        color: @normal-color;

        &:hover {
          color: @hover-color;
        }
      }
    }
  }
</style>
