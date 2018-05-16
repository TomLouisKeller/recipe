### Livereload

guard 'livereload' do
  extensions = {
    css: :css,
    scss: :css,
    sass: :css,
    js: :js,
    coffee: :js,
    html: :html,
    png: :png,
    gif: :gif,
    jpg: :jpg,
    jpeg: :jpeg,
    # less: :less, # uncomment if you want LESS stylesheets done in browser
  }

  rails_view_exts = %w(erb haml slim)

  # file types LiveReload may optimize refresh for
  compiled_exts = extensions.values.uniq
  watch(%r{public/.+\.(#{compiled_exts * '|'})})

  extensions.each do |ext, type|
    watch(%r{
           (?:/src/main/resources/\w+/(?<path>[^.]+) # path+base without extension
           (?<ext>\.#{ext})) # matching extension (must be first encountered)
          (?:\.\w+|$) # other extensions
          }x) do |m|
      path = m[1]
      "/assets/#{path}.#{type}"
    end
  end

  #          (?:app|vendor)
  #          (?:/assets/\w+/(?<path>[^.]+) # path+base without extension

  # file needing a full reload of the page anyway
#  watch(%r{src/main/resources/views/.+\.(#{rails_view_exts * '|'})$})
#  watch(%r{src/main/resources/helpers/.+\.rb})
#  watch(%r{src/main/resources/locales/.+\.yml})
end
